/*
 * Copyright 2016 lum.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.lum.common

import java.io.{ File, FileFilter }
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOCase
import org.apache.commons.io.filefilter.{ RegexFileFilter, WildcardFileFilter }

object FileUtils {

  implicit class FileWrapper(val file: File) extends AnyVal {

    /** Gets the base name, minus the full path and extension. */
    def getBaseName: String = FilenameUtils.getBaseName(file.getPath())

    /** Gets the extension of a file. */
    def getExtension: String = FilenameUtils.getExtension(file.getPath())

    /** Checks a file to see if it matches the specified wildcard matcher allowing control over case-sensitivity. */
    def wildcardMatch(wildcardMatcher: String, caseSensitive: Boolean = true): Boolean = {
      val caseSensitivity = if (caseSensitive) IOCase.SENSITIVE else IOCase.INSENSITIVE
      FilenameUtils.wildcardMatch(file.getPath(), wildcardMatcher, caseSensitivity)
    }

    def listFilesByRegex(pattern: String, caseSensitive: Boolean = true): Array[File] = {
      val caseSensitivity = if (caseSensitive) IOCase.SENSITIVE else IOCase.INSENSITIVE
      val fileFilter: FileFilter = new RegexFileFilter(pattern, caseSensitivity)
      file.listFiles(fileFilter)
    }

    def listFilesByWildcard(wildcard: String, caseSensitive: Boolean = true): Array[File] = {
      val caseSensitivity = if (caseSensitive) IOCase.SENSITIVE else IOCase.INSENSITIVE
      val fileFilter: FileFilter = new WildcardFileFilter(wildcard, caseSensitivity)
      file.listFiles(fileFilter)
    }

  }

}

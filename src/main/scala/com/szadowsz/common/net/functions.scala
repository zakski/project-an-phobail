package com.szadowsz.common.net

import scala.util.matching.Regex

/**
  * Created on 16/05/2016.
  */
private[net] object functions {

  implicit class RegexUtil(regex: Regex) {
    def matches(s: String) = regex.pattern.matcher(s).matches

    def matches(c: Character) = regex.pattern.matcher(c.toString).matches
  }
}

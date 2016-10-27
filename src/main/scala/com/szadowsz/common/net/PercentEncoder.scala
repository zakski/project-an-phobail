package com.szadowsz.common.net

import functions._

/**
  * Created on 15/05/2016.
  */
private[net] object PercentEncoder {
  private val alpha = "[a-zA-Z]"
  private val digit = "\\d"
  private val unreserved = "[" + alpha + digit + "\\.-_~]"
  private val reserved = "[\\?\\*\\+\\(\\)\\$:/#\\[\\]@!&',;=]"

  private val alphaR = alpha.r
  private val digitR = digit.r
  private val unreservedR = unreserved.r
  private val reservedR = reserved.r
  private val unencodeable = ("(" + unreserved + "|" + reserved + ")").r

  private def shouldEncode(ch: Char): Boolean = !unencodeable.matches(ch)

  private def encode(ch: Char): String = "%" + "%04X".format(ch.toInt).substring(2)

  def encode(s: String): String = {
    val chars = s.getBytes("UTF-8").map(_.toChar)

    val encChars = chars.flatMap(ch => {
      if (shouldEncode(ch)) {
        encode(ch).getBytes("UTF-8")
      } else {
        Array(ch.toByte)
      }
    })

    new String(encChars, "UTF-8")
  }
}

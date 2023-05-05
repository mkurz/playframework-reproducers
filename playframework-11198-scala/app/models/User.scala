/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package models

import scala.Left
import scala.Right

import play.api.mvc.{PathBindable, QueryStringBindable}
import play.api.Logging

//#declaration
case class User(id: Int, name: String, from: Int, to: Int) {}
//#declaration
object User extends Logging {
  // stubbed test
  // designed to be lightweight operation
  def findById(id: Int): Option[User] = {
    logger.info("findById: " + id.toString)
    if (id > 3) None
    var user = new User(id, "User " + String.valueOf(id), -1, -2)
    Some(user)
  }

  // #bind
  implicit def pathBinder(implicit intBinder: PathBindable[Int]): PathBindable[User] = new PathBindable[User] {
    override def bind(key: String, value: String): Either[String, User] = {
      for {
        id   <- intBinder.bind(key, value)
        user <- User.findById(id).toRight("User not found")
      } yield user
    }
    override def unbind(key: String, user: User): String = {
      user.id.toString
    }
  }

  implicit def queryStringBindable(implicit intBinder: QueryStringBindable[Int]): QueryStringBindable[User] =
    new QueryStringBindable[User] {
      override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, User]] = {
        for {
          from <- intBinder.bind("from", params)
          to <- intBinder.bind("to", params)
        } yield {
          (from, to) match {
            case (Right(from), Right(to)) => Right(User(-100, "no_name", from, to))
            case _ => Left("Unable to bind an User")
          }
        }
      }

      override def unbind(key: String, User: User): String = {
        intBinder.unbind("from", User.from) + "&" + intBinder.unbind("to", User.to)
      }
    }
  // #bind
}

package com.stephentu.sql

import java.util.Properties

// these are the types of relations which can show up in a
// FROM clause
abstract trait Relation
case class TableRelation(name: String) extends Relation
case class SubqueryRelation(stmt: SelectStmt) extends Relation

case class TableColumn(name: String, tpe: DataType) extends PrettyPrinters {
  def scalaStr: String =
    "TableColumn(" + _q(name) + ", " + tpe.toString + ")"
}

class Definitions(val defns : Map[String, Seq[TableColumn]]) extends PrettyPrinters {

  def tableExists(table: String): Boolean = defns.contains(table)

  def lookup(table: String, col: String): Option[TableColumn] = {
    defns.get(table).flatMap(_.filter(_.name == col).headOption)
  }

  def scalaStr: String = {
    "new Definitions(Map(" + (defns.map { case (k, v) =>
      (_q(k), "Seq(" + v.map(_.scalaStr).mkString(", ") + ")")
    }.map { case (k, v) => k + " -> " + v }.mkString(", ")) + "))"
  }
}


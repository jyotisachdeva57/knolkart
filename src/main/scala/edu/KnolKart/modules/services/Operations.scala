package edu.KnolKart.modules.services

import edu.KnolKart.modules.functionentity.Product
import edu.KnolKart.modules.functionentity.Vendor
import org.apache.log4j.Logger

import scala.collection.immutable.ListMap

class Operations[A](products: Map[Int, A]) {

  def takeInput: Product = {
    import scala.io.StdIn._
    val log = Logger.getLogger(this.getClass)
    log.info("\n ENTER PRODUCT ID")
    val productID = readInt()
    log.info("ENTER PRODUCT NAME\n")
    val productName = readLine()
    log.info("ENTER PRODUCT DESCRIPTION\n")
    val productDesc = readLine()
    log.info("ENTER PRODUCT CATEGORY\n")
    val productCategory = readLine()
    log.info("ENTER PRODUCT PRICE\n")
    val productPrice = readDouble()
    log.info("ENTER PRODUCT COUNT\n")
    val productCount = readInt()
    log.info("ENTER VENDOR NAME\n")
    val vendorName = readLine()
    log.info("ENTER VENDOR ID\n")
    val vendorID = readInt()
    Product(productID, productName, productDesc, productCategory, productPrice, productCount, Vendor(vendorName, vendorID))
  }


  def updateItemCount(products: Map[Int, A], itemID: Int, updateBy: Int, f: (Int, Int) => Int): Map[Int, A] = {
    val obj = products(itemID)
    obj match {
      case pro: Product =>
        val current=pro.asInstanceOf[Product]
        val hold = products - itemID
        val itemWithNewCount = current.copy(productCount = f(current.productCount, updateBy))
        val res = hold + (itemID -> itemWithNewCount.asInstanceOf[A])
        res
      case _ => products
    }
  }


  def findByID(products: Map[Int, A], itemID: Int): Map[Int, A] = {
    products.filter((t) => t._1 == itemID)
  }


  def findByCategory(products: Map[Int, A], categoryFilter: String): Map[Int, A] = {
    if (products.nonEmpty) {
      val (a, b) = products.head
      if (b.isInstanceOf[Product]) {
        products.filter((t) => t._2.asInstanceOf[Product].category == categoryFilter)
      }
      else {
        products
      }

    }
    else {
      products
    }
  }


  def addItem(products: Map[Int, A], item: A, key: Int): Map[Int, A] = {
    products + (key -> item)
  }

  def removeItem(products: Map[Int, A], key: Int): Map[Int, A] = {
    products - key
  }

  def sortProducts(products: Map[Int, A], sortParameter: String): Map[Int, A] = {
    if (products.nonEmpty) {
      val (a, b) = products.head
      if (b.isInstanceOf[Product]) {
        sortParameter.toLowerCase match {
          case "low to high" => ListMap(products.toSeq.sortWith(_._2.asInstanceOf[Product].price < _._2.asInstanceOf[Product].price): _*)
          case "high to low" => ListMap(products.toSeq.sortWith(_._2.asInstanceOf[Product].price > _._2.asInstanceOf[Product].price): _*)
          case "default" => ListMap(products.toSeq.sortWith(_._2.asInstanceOf[Product].price < _._2.asInstanceOf[Product].price): _*)
        }
      }
      else {
        products
      }
    }
    else {
      products
    }
  }
}




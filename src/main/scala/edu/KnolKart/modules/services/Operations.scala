package edu.KnolKart.modules.services

import edu.KnolKart.modules.functionentity.Product
import edu.KnolKart.modules.functionentity.Vendor
import org.apache.log4j.Logger

import scala.collection.immutable.ListMap

class Operations[A](products: Map[Int, A]) {


  def sortProducts(products: Map[Int, Product], sortParameter: String): Map[Int, Product] = {
    sortParameter.toLowerCase match {

      case "low to high" => ListMap(products.toSeq.sortWith(_._2.price < _._2.price): _*)

      case "high to low" => ListMap(products.toSeq.sortWith(_._2.price > _._2.price): _*)

      case _ => ListMap(products.toSeq.sortWith(_._2.price < _._2.price): _*)
    }
  }


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

  def updateItemCount(products: Map[Int, Product], itemID: Int, updateBy: Int, f: (Int, Int) => Int): Map[Int, Product] = {
    // val hold=products.map(x => if(x._1 == itemID) (x._1, x._2.productCount - updateBy) else (x._1, x._2))
    //
    //    val productOldCount = products.filter((t) => t._1 == itemID)
    //    val hope=productOldCount.get(itemID)
    val obj = products(itemID)
    val hold = products - itemID
    val itemWithNewCount = obj.copy(productCount = f(obj.productCount, updateBy))
    val res = hold + (itemID -> itemWithNewCount)
    res
  }


  def findByID(products: Map[Int, Product], itemID: Int): Map[Int, Product] = {
    products.filter((t) => t._1 == itemID)
  }


  def findByCategory(products: Map[Int, Product], categoryFilter: String): Map[Int, Product] = {
    products.filter((t) => t._2.category == categoryFilter)
  }


  def addItem(products: Map[Int, A], item: A, key: Int): Map[Int, A] = {
    products + (key -> item)
  }

  def removeItem(products: Map[Int, A], key: Int): Map[Int, A] = {
    products - key
  }


}



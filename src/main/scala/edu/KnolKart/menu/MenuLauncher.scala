package edu.KnolKart.menu

import edu.KnolKart.modules.functionentity.Product
import edu.KnolKart.modules.services.Operations
import org.apache.log4j.Logger
import scala.io.StdIn._

object MenuLauncher {

  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val obj = new Operations[Product](Map[Int, Product]())
    log.info("ADD PRODUCT\n")
    val input = obj.takeInput
    val product1 = obj.addItem(Map[Int, Product](), input, input.productID)
    val input1 = obj.takeInput
    val product2 = obj.addItem(product1, input1, input1.productID)
    log.info(" PRODUCTS ARE \n")
    val products = obj.sortProducts(product2, "default")
    val productList = products.valuesIterator.toList
    log.info(productList)
    log.info("SORT PRODUCT LOW TO HIGH\n")
    log.info(obj.sortProducts(product2, "low to high"))
    log.info(" SORT PRODUCT HIGH TO LOW\n")
    log.info(obj.sortProducts(product2, "high to low"))
    log.info("FIND PRODUCT BY ID\n")
    log.info("ENTER ID\n")
    val proID = readInt()
    log.info(obj.findByID(product2, proID))
    log.info("FIND PRODUCT BY CATEGORY\n")
    log.info("ENTER CATEGORY \n")
    val proCat = readLine()
    log.info(obj.findByCategory(product2, proCat))
    log.info("UPDATE COUNT\n")
    log.info("ENTER COUNT\n")
    val count = readInt()
    log.info(obj.updateItemCount(product2, 1, count, (a, b) => a - b))
  }

}


package edu.KnolKart.menu

import edu.KnolKart.modules.functionentity.Product
import edu.KnolKart.modules.services.Operations
import org.apache.log4j.Logger

import scala.io.StdIn._

object MenuLauncher {
  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val obj = new Operations[Product](Map[Int, Product]())
//    log.info("MENU\n")
//    log.info("1.ADD PRODUCT\n")
//    log.info("2.LIST PRODUCTS\n")
//    log.info("3.SORT PRODUCT LOW TO HIGH\n")
//    log.info("4. SORT PRODUCT HIGH TO LOW\n")
//    log.info("5.FIND PRODUCT BY ID\n")
//    log.info("6.FIND PRODUCT BY CATEGORY\n")
//    log.info("7.UPDATE COUNT\n")
//    val choice = readInt()
//    choice match {
//      case 1 =>
        val input = obj.takeInput
        val h1=obj.addItem(Map[Int,Product](), input, 1)
        val input1 = obj.takeInput
        val h2=obj.addItem(h1, input1, 2)
    print("\n product map is\n")
    print(h2)

//   print( "find by category\n"+obj.findByCategory(h2,"a"))
//
//
//    print("by id "+obj.findByID(h2,1))
//    print("sort products\n")
//    print(obj.sortProducts(h2,"low to high"))
//   print( obj.sortProducts(h2,"high to low"))
 obj.removeItem(h1,1)
//println(obj.updateItemCount(h2,1,2,(a,b)=>a-b))
    }
  }


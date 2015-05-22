/**
 * Created by Aozi on 23.5.2015.
 */
class DungeonMap(height: Int, width: Int, numRooms: Int) {

  val rnd = new scala.util.Random

  var myMap = Array.fill(height,width)(0)

  var roomList = List.fill(numRooms)(genRoom())

  /*
  Generates a random room with at least 2 height and width
   */
  def genRoom(): Array[Array[Int]] = Array.fill(rnd.nextInt(6)+2,rnd.nextInt(6)+2)(1)


  /*
  For printing the entire map
   */

  override def toString(): String = myMap.map(_.mkString).mkString("\n")

}
/*
0 = wall
1 = floor
 */
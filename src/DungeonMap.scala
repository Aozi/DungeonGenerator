import java.util

/**
 * Created by Aozi on 23.5.2015.
 */
class DungeonMap(height: Int, width: Int, numRooms: Int) {

  val rnd = new scala.util.Random

  var myMap = Array.fill(height,width)(0)

  var roomList = List.fill(numRooms)(genRoom())

  var doorList = List


  for(roomToAdd <- roomList) {
    var result = true
    while(result) {
      if(placeRoom(roomToAdd) == true) result = false
    }
  }

  def addToDoorList(x: Int, y: Int): Unit = {
    doorList.add(new Point(x,y))
  }
  /*
  Generates a random room with at least 3 height and width
   */
  def genRoom(): Array[Array[Int]] =  {
    var a = Array.fill(rnd.nextInt(6)+3,rnd.nextInt(6)+3)(1)
    rnd.nextInt(3) match {
      case 0 => a(0)(a(0).length/2) = 3;
        //addToDoorList(0,a(0).length/2)

      case 1 => a(a.length/2)(0) = 3;
        //addToDoorList(a.length/2,0)

      case 2 => a(a.length-1)(a(0).length/2) = 3;
        //addToDoorList(a.length-1,a(0).length/2)

      case 3 => a(a.length/2)(a(0).length-1) = 3;
        //addToDoorList(a.length/2,a(0).length-1)

    }

    return a
  }

  /*
  Selects a random spot in the dungeon map to place the room in.
   */

  def roomSpot(): Point = new Point(rnd.nextInt(height),rnd.nextInt(width))

  /*
  Checks if the current room being tried can be places on the specified spot
   */
  def doesItFit(h: Int, w: Int, p: Point): Boolean = {
    if(p.x+h >= myMap.length || p.y+w >= myMap(0).length) return false
    for(i2 <- 0 to h) {
      for(j2 <- 0 to w) {
        if(myMap(p.x+i2)(p.y+j2) != 0) return false;
      }
    }
    return true
  }

/*
Places a room on the dungeon
 */
  def placeRoom(curRoom: Array[Array[Int]]) : Boolean = {
  val p = roomSpot()
  var a = 1
    if(doesItFit(curRoom.length, curRoom(0).length, p)) {
      for(i <- 1 to curRoom.length) {
        for(j <- 1 to curRoom(0).length) {
          a = curRoom(i-1)(j-1)
          //print(i)
          //print(j)
          //print(sys.props("line.separator"))
          myMap(p.x+i)(p.y+j) = a
        }
      }

    }
    else return false
  return true
  }


  /*
  A class to define a point for easier implementation of matrix insertion
   */
  class Point(xc: Int, yc: Int) {
    var x: Int = xc
    var y: Int = yc
  }

  /*
  For printing the entire map
   */

  override def toString(): String = myMap.map(_.mkString).mkString("\n")

}
/*
0 = wall
1 = floor
3 = Door
 */
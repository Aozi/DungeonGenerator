/**
 * Created by Aozi on 23.5.2015.
 */
class DungeonMap(height: Int, width: Int, numRooms: Int) {

  val rnd = new scala.util.Random

  var myMap = Array.fill(height,width)(0)

  var roomList = List.fill(numRooms)(genRoom())

  for(roomToAdd <- roomList) {
    var result = true
    while(result) {
      if(placeRoom(roomToAdd) == true) result = false
    }
  }

  /*
  Generates a random room with at least 3 height and width
   */
  def genRoom(): Array[Array[Int]] = Array.fill(rnd.nextInt(6)+3,rnd.nextInt(6)+3)(1)

  /*
  Selects a random spot in the dungeon map to place the room in.
   */

  def roomSpot(): Point = new Point(rnd.nextInt(height),rnd.nextInt(width))

  /*
  Checks if the current room being tried can be places on the specified spot
   */
  def doesItFit(h: Int, w: Int, p: Point): Boolean = {
    if(p.x+h > myMap.length || p.y+w > myMap(0).length) return false

    for(i <- 0 to h) {
      for(j <- 0 to w) {
        if(myMap(p.x+i)(p.y+j) != 0) return false;
      }
    }
    return true
  }

/*
Places a room on the dungeon
 */
  def placeRoom(curRoom: Array[Array[Int]]) : Boolean = {
  var p = roomSpot()
    if(doesItFit(curRoom.length, curRoom(0).length, p)) {
      for(i <- 0 to curRoom.length) {
        for(j <- 0 to curRoom(0).length) {
          myMap(p.x+i)(p.y+j) = 1
        }
      }

    }
    else return false
  return true
  }

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
 */
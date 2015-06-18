import scala.collection.mutable.{ListBuffer, ArrayBuffer}

/**
 * Main class for the dungeon generation
 * @constructor Create a new dungeon with the specified size
 * @param height The height of the dungeon
 * @param width Width of the dungeon
 * @param numRooms Number of rooms to generate for the dungeon
 */
class DungeonMap(height: Int, width: Int, numRooms: Int) {

  val doorList = new ListBuffer[Point]()

  val rnd = new scala.util.Random

  var myMap = Array.fill(height,width)(0)

  var roomList = List.fill(numRooms)(genRoom())

  for(roomToAdd <- roomList) {
    var result = true
    while(result) {
      if(placeRoom(roomToAdd)) result = false
    }
  }

  /**
   * Adds a specific cell to the list of doors for the pathfinding
   * @param x X coordinate for the door
   * @param y Y coordinate for the door
   */

  def addToDoorList(x: Int, y: Int) = {

    val p = new Point(x,y)
    doorList += p
  }

  /**
   * Generates a room of 3-9 cells high/wide and returns the corresponding matrix
   * @return Returns the matrix of the room
   */
  def genRoom(): Array[Array[Int]] =  {
    var a = Array.fill(rnd.nextInt(6)+3,rnd.nextInt(6)+3)(1)
    rnd.nextInt(3) match {
      case 0 => a(0)(a(0).length/2) = 3
        addToDoorList(0,a(0).length/2)

      case 1 => a(a.length/2)(0) = 3
        addToDoorList(a.length/2,0)

      case 2 => a(a.length-1)(a(0).length/2) = 3
        addToDoorList(a.length-1,a(0).length/2)

      case 3 => a(a.length/2)(a(0).length-1) = 3
        addToDoorList(a.length/2,a(0).length-1)

    }

    a
  }

  /**
   * Selects a random location on the entire dungeon map for a room
   * @return Return a Point with the x and y coordinates of the chosen spot
   */
  def roomSpot(): Point = new Point(rnd.nextInt(height),rnd.nextInt(width))

  /**
   * Checks if given room fits on a specified spot
   * @param h Height of the room
   * @param w Width of the room
   * @param p Point to test
   * @return Return true if the room fits, false if it does not
   */
  def doesItFit(h: Int, w: Int, p: Point): Boolean = {
    if(p.x+h >= myMap.length || p.y+w >= myMap(0).length) return false
    for(i2 <- 0 to h) {
      for(j2 <- 0 to w) {
        if(myMap(p.x+i2)(p.y+j2) != 0) false
      }
    }
    true
  }


  /**
   * Method to place a room on the current dungeon map
   * @param curRoom 2D matrix representing the room to place
   * @return returns true if placement succeeds
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
    else false
  true
  }

  /**
   * Method to print the entire map
   * @return Returns a string representation of the map
   */

  override def toString(): String = myMap.map(_.mkString).mkString("\n")

}
/*
0 = wall
1 = floor
3 = Door
 */
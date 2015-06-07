import scala.collection.mutable.ListBuffer

/**
 * @constructor
 * @param map 2D array representing the current dungeon map
 */
class pathFinder (map: Array[Array[Int]]) {

  val closedList = new ListBuffer[Point]

  val openList = new ListBuffer[Point]

  /**
   * Finds the path from the start point to an end point
   * @param start Point(x,y) where we start
   * @param end Point(x,y) where we need to get
   * @return Returns a list of points that represent the path from start to end
   */

  def findPath(start: Point, end: Point): ListBuffer[Point] = {

    var returnList = new ListBuffer[Point]



    return returnList
  }


  /**
   * Calculates the value of the current cell to the end cell
   * @param currCell The cell to check
   * @param endPoint The point to reach
   * @return Return the value of the calculation
   */

  def calcCellValue(currCell: Point, endPoint: Point): Int = {

    return 0
  }
}

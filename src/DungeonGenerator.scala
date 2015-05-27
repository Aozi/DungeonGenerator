/**
 * Created by Aozi on 17.5.2015.
 */
object DungeonGenerator {

    def main(args: Array[String]) {
      //print("Hello world")

      val dMap = new DungeonMap(50,50,8)

      //print(dMap.myMap.length)
      //print(dMap.myMap(0).length)

      for (p <- dMap.doorList) {
        print("yay")
      }

      print(dMap)

  }
}

package playground

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ClosedShape
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, RunnableGraph, Sink, Source, Zip}

object GraphPlayground extends App {

  implicit val system = ActorSystem("GraphBasics")

  val input = Source(1 to 10)

  val incrementer = Flow[Int].map(x => x + 1)
  val squareFlow = Flow[Int].map(x => s"Square: ${(x+1)*(x+1)}")

  val output = Sink.foreach[(Int, String)](println)

  val graph = RunnableGraph.fromGraph(
    GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
      import GraphDSL.Implicits._

      val broadcast = builder.add(Broadcast[Int](2)) // fan-out operator
      val zip = builder.add(Zip[Int, String]) // fan-in operator

      input ~> broadcast

      broadcast.out(0) ~> incrementer ~> zip.in0
      broadcast.out(1) ~> squareFlow ~> zip.in1

      zip.out ~> output

      ClosedShape
    }
  )

  graph.run()
}

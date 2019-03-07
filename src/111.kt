import java.math.BigInteger

fun main(args: Array<String>) {
    val matrix = arrayOf(
            intArrayOf(0, 1, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0))

    Struct(matrix).update(6)
}

class Struct(private val graph: Array<IntArray>) {

    private var nodeInfo: Array<NumPair> = Array(graph.size, { NumPair(1, graph.size.toLong()) })
    private val scale = NumPair(4, 5)
    private val invScale = NumPair(1, 5)

    fun update(count: Int) {
        nodeInfo.forEach { print("$it ") }.also { println() }
        var newNodeInfo: Array<NumPair>
        var i = 0
        do {
            newNodeInfo = Array(graph.size, { NumPair(0, 1) })
            for (index in 0 until nodeInfo.size) {
                (0 until graph[index].size).filter { graph[index][it] == 1 }.forEach {
                    newNodeInfo[it] += scale * nodeInfo[index] / graph[index].count { it == 1 }
                }
            }
            newNodeInfo.forEach { it += (invScale * NumPair(1, graph.size.toLong())) }
            newNodeInfo.forEach { print("$it ") }.also { println() }
            nodeInfo = newNodeInfo
        } while (++i <= count)
    }
}

data class NumPair(private var first: Long, private var second: Long) {

    init {
        gcd()
    }

    operator fun plusAssign(numPair: NumPair) {
        this.first = this.first * numPair.second + numPair.first * this.second
        this.second *= numPair.second
        gcd()
    }

    operator fun times(numPair: NumPair) =
            NumPair(this.first * numPair.first, this.second * numPair.second).gcd()

    operator fun div(num: Int) = NumPair(this.first, this.second * num).gcd()

    private fun gcd(): NumPair {
        val gcd = BigInteger.valueOf(first).gcd(BigInteger.valueOf(second)).toLong()
        if (gcd != 0L) {
            this.first /= gcd
            this.second /= gcd
        }
        return this
    }

    override fun toString() = when (first) {
        second -> "1"
        0L -> "0"
        else -> "$first/$second"
    }
}
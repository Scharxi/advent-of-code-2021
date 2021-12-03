enum class Direction {
    Up, Down, Forward
}

data class Move(val dir: Direction, var steps: Int)

fun main() {
    fun part1(input: List<Move>): Int {
        var depth: Int = 0
        var horizontal: Int = 0
        for (move in input) {
            when (move.dir) {
                Direction.Forward -> horizontal += move.steps
                Direction.Down -> depth += move.steps
                Direction.Up -> depth -= move.steps
            }
        }
        return depth * horizontal
    }

    fun part2(input: List<Move>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0
        for (move in input) {
            when (move.dir) {
                Direction.Down -> aim += move.steps
                Direction.Up -> aim -= move.steps
                Direction.Forward -> run { horizontal += move.steps; depth += aim * move.steps }
            }
        }
        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").map { it.split(" ") }
        .map { (dir, amount) -> Move(Direction.valueOf(dir.capitalize()), amount.toInt()) }
    check(part2(testInput) == 900)

    val input = readInput("Day02").map { it.split(" ") }
        .map { (dir, amount) -> Move(Direction.valueOf(dir.capitalize()), amount.toInt()) }
    println(part2(input))
}

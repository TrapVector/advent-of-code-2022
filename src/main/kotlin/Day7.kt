object Day7 : Day(7) {

    class Directory (val parent: Directory? = null) {
        val directories: MutableMap<String, Directory> = mutableMapOf()
        var totalSize: Int = 0

        fun addDirectory(childName: String) : Directory {
            val newDirectory = Directory(this)
            directories[childName] = newDirectory
            return  newDirectory
        }

        fun updateSize(size: Int) {
            totalSize += size
            parent?.updateSize(size)
        }

    }
    override fun getSolution(): Solution {
        val rootDirectory = Directory()
        var currentDirectory = rootDirectory
        val allDirectories: MutableList<Directory> = mutableListOf()

        val handleChangeDirectory = { dirName: String ->
            currentDirectory = when (dirName) {
                "/" ->  rootDirectory
                ".." -> currentDirectory.parent ?: rootDirectory
                else -> currentDirectory.directories[dirName] ?: currentDirectory
            }
        }

        getInputFile().forEachLine {
            val parts = it.split(' ')
            if (parts[0] == "$") {
                if (parts[1] == "cd") {
                    handleChangeDirectory(parts[2])
                }
            }
            else {
                if (parts[0] == "dir") {
                    val newDirectory = currentDirectory.addDirectory(parts[1])
                    allDirectories.add(newDirectory)
                }
                else {
                    currentDirectory.updateSize(parts[0].toInt())
                }
            }
        }

        val totalDirectorySize = allDirectories.fold(0) { total, dir ->
            if (dir.totalSize <= 100000) {
                total + dir.totalSize
            }
            else {
                total
            }
        }

        val totalSize = 70000000
        val usedSize = rootDirectory.totalSize
        val updateSize = 30000000
        val freeSize = totalSize - usedSize
        val requiredFreeSize = updateSize - freeSize
        val bestDirectorySize = allDirectories.filter {
            it.totalSize >= requiredFreeSize
        }.minBy { it.totalSize }.totalSize

        return Solution(
            totalDirectorySize.toString(),
            bestDirectorySize.toString()
        )
    }
}
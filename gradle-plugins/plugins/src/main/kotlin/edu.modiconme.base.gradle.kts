version = file(rootDir.path.plus("/version.txt")).readText().trim()

group =
    "edu.modiconme"

/*
                   val ci = providers.environmentVariable("CI").getOrElse("false").toBoolean()
                   println("Using ci ? $ci")
                   */

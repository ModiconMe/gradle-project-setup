plugins { id("edu.modiconme.lifecycle") }

defaultTasks("tasks")

tasks.named<TaskReportTask>("tasks") { displayGroup = "build" }

package org.shmurda.bardsimpson.util.version

class VersionInfo(
    var branch: String?,
    var commit: String?,
    var commitUser: String?,
    var commitMessage: String?,
    var commitTime: String?
) {

    constructor() : this("Unable to find branch.", "Unable to find commit.", "Unable to find commit.", "Unable to find commit.", "Unable to find commit.")

}

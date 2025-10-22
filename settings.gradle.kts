rootProject.name = "DevOps"

buildCache {
    val cacheUrlEnv = System.getenv("GRADLE_REMOTE_CACHE_URL")
    if (cacheUrlEnv != null) {
        remote<HttpBuildCache> {
            url = uri(cacheUrlEnv)
            isAllowInsecureProtocol = true
            isAllowUntrustedServer = true
            isPush = System.getenv("GRADLE_REMOTE_CACHE_PUSH")?.toBoolean() ?: false
            credentials {
                username = System.getenv("GRADLE_REMOTE_CACHE_USERNAME")
                password = System.getenv("GRADLE_REMOTE_CACHE_PASSWORD")
            }
        }
    }
}


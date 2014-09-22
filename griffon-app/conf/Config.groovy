application {
    title = 'awana-database'
    startupGroups = ['awanaDatabase']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "awanaDatabase"
    'awanaDatabase' {
        model      = 'com.liddev.AwanaDatabaseModel'
        view       = 'com.liddev.AwanaDatabaseView'
        controller = 'com.liddev.AwanaDatabaseController'
    }
}
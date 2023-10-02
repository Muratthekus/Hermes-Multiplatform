import SwiftUI
import shared
import Combine

@main
@available(iOS 15.0, *)
struct iOSApp: App {
    
    
    init() {
        UserDefaultsUtils.sharedUserDefaults.setDarkMode(enable: true)
    }
    
    var body: some Scene {
        WindowGroup {
            NavigationView {
                ContentView()
            }
            
        }
    }
}

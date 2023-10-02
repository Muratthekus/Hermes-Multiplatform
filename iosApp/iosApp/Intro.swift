import SwiftUI
import shared
import Combine
import UIKit

@available(iOS 15.0, *)
struct ContentView: View {
    @Environment(\.colorScheme) private var colorScheme: ColorScheme

    init() {
        UINavigationBar.appearance().barTintColor = UIColor(named: "Accent")
        UINavigationBar.appearance().backgroundColor = UIColor(named: "BackgroundColor")
        UITableView.appearance().backgroundColor = UIColor(named: "BackgroundColorList")
        //deneme(state: true)
    }
    
    
    func changeDarkMode(state: Bool){
        if let window = UIApplication.shared.windows.first {
             window.overrideUserInterfaceStyle = state ? .dark : .light
         }
         UserDefaultsUtils.sharedUserDefaults.setDarkMode(enable: state)
    }
    
    
	var body: some View {
        ScrollView {
            LazyHStack {
                PageView()
            }.background(Color.BackgroundColorList)
        }
	}
}

@available(iOS 15.0, *)
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        PageView()
            .environment(\.colorScheme, .light)
    }
}

@available(iOS 15.0, *)
struct PageView: View {
    
    @State private var showButton = true
    
    func getIntroItemList() -> Array<String> {
        return ["Connect with people without centralized connections", "Hermes uses Bluetooth, Wifi and Location to build connection", "Everything happen in local. All your data will be kept in your device","Welcome to Hermes"]
    }
    
    var body: some View {
        TabView {
            ForEach(0..<4) { i in
                ZStack {
                    VStack {
                         Image(uiImage: UIImage(named: "illustration")!)
                             .resizable()
                             .frame(width: 270, height: 270)
                             .aspectRatio(contentMode: .fit)
                             .padding()
                        
                         Text(getIntroItemList()[i])
                             .multilineTextAlignment(.center)
                             .padding(.top, 40)
                             .padding(.bottom, 60)
                             .padding(.horizontal, 45)
                             .font(.headline)
                             .foregroundColor(Color.OnBackground)
                        
                        if i == 3 {
                            IntroButton()
                        }
                     }
                }
                .frame(maxWidth: .infinity)
                .foregroundColor(Color.BackgroundColor)
                .clipShape(RoundedRectangle(cornerRadius: 10.0, style: .continuous))
                    .background(Color.clear)
                    
            }
        }.background(Color.BackgroundColorList)
            .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height)
        .tabViewStyle(PageTabViewStyle())
        .onAppear() {
            // Change the PageControl dots color
            UIPageControl.appearance().currentPageIndicatorTintColor = .blue
            UIPageControl.appearance().pageIndicatorTintColor = .gray
            
        }
    }
}

@available(iOS 15.0, *)
struct IntroButton: View {
    
    @State private var isButtonClicked = false
    private let mModule = OtpUseCaseModule()
    
    var body: some View {
        ZStack {
            NavigationLink(destination: RegisterScreen(otpUseCase: mModule.otpUseCase), isActive: $isButtonClicked) {
                EmptyView()
            }.hidden()
            
            Button(action: {
                isButtonClicked = true
            }) {
                Text("Start messaging")
                    .foregroundColor(Color.BackgroundColor)
            }
            .frame(width: .infinity, height: 55)
            .padding(.horizontal, 100)
            .animation(.easeInOut)
            .foregroundColor(Color.OnPrimary)
            .background(
                RoundedRectangle(cornerRadius: 18) // Adjust corner radius as needed
                            .foregroundColor(Color.Accent) // Background color
            )
            
        }
        
    }

}

//
//  RegisterViewModel.swift
//  iosApp
//
//  Created by Murat Kuş on 11.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension RegisterScreen {
    @MainActor class RegisterViewModel: ObservableObject {
        private var otpUseCase: OtpUseCase? = nil
    
        @Published private(set) var isLoading: Bool = false
        @Published private(set) var isError: Error? = nil
        @Published private(set) var isSuccess: Bool = false
        
        init(otpUseCase: OtpUseCase? = nil) {
            self.otpUseCase = otpUseCase
        }
        
        
        func setOtpUseCase(otpUseCase: OtpUseCase?) {
            self.otpUseCase = otpUseCase
        }
        
        func signUpUser(email: String, password: String) {
            self.isLoading = true
            otpUseCase?.signupUser(email: email, password: password) { error in
                self.isError = error
                self.isSuccess = false
            }
            self.isLoading = false
            self.isSuccess = true
            
            if self.isSuccess {
                let kmmStorage = KMMStorage(context: NSObject())
                kmmStorage.putString(key: Constants.EMAIL_KEY, value: email)
                kmmStorage.putString(key: Constants.PASSWORD_KEY, value: password)
            }
        }
        
    }
}

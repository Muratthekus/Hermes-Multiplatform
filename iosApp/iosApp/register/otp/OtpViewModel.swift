//
//  OtpViewModel.swift
//  iosApp
//
//  Created by Murat Kuş on 12.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension OtpScreen {
    
    @MainActor class OtpViewModel: ObservableObject {
        private var otpUseCase: OtpUseCase? = nil
        
        @Published private(set) var isSuccess: Bool = false
        @Published private(set) var isLoading: Bool = false
        
        init(otpUseCase: OtpUseCase? = nil) {
            self.otpUseCase = otpUseCase
        }
        
        
        func setOtpUseCase(otpUseCase: OtpUseCase) {
            self.otpUseCase = otpUseCase
        }
        
        func resendOtp() {
            let kmmStorage = KMMStorage(context: NSObject())
            let email = kmmStorage.getString(key: Constants.EMAIL_KEY, defValue: "")
            otpUseCase?.resendOtp(email: email, completionHandler: { error in
                //
            })
        }
        
        func verifyEmailOtp(token: String) {
            self.isLoading = true
            let kmmStorage = KMMStorage(context: NSObject())
            let email = kmmStorage.getString(key: Constants.EMAIL_KEY, defValue: "")
            otpUseCase?.verifyEmailOtp(email: email, token: token, completionHandler: { isSuccess, error in
                DispatchQueue.main.async {
                    self.isLoading = false
                    if isSuccess != nil{
                        self.isSuccess = (isSuccess != nil && isSuccess == true)
                        kmmStorage.putBoolean(key: Constants.IS_OTP_COMPLETED, value: true)
                    }
                }
            })
        }
    }
}

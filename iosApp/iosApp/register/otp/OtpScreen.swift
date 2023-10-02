//
//  OtpScreen.swift
//  iosApp
//
//  Created by Murat Kuş on 12.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct OtpScreen: View {
    @StateObject var viewmodel = OtpViewModel(otpUseCase: nil)
    private let mModule = OtpUseCaseModule()
    private let otpUseCase: OtpUseCase
    private let countDownTimer = CountDownTimer()
    
    init() {
        self.otpUseCase = mModule.otpUseCase
    }
    
    var body: some View {
        
        
        ZStack {
            VStack {
                Text("Enter Code")
                    .foregroundColor(Color.OnBackground)
                    .font(.headline)
                
                Text("We have sent you an OTP code")
                    .multilineTextAlignment(.center)
                    .padding(.bottom, 30)
                    .padding(.horizontal, 45)
                    .font(.headline)
                    .foregroundColor(Color.OnBackground)
                
                OtpFormFieldView { otpTextView in
                    viewmodel.verifyEmailOtp(token: otpTextView)
                }
                
                CountDownView(countDownTimer: self.countDownTimer) {
                    self.countDownTimer.stopTimer()
                    viewmodel.resendOtp()
                    self.countDownTimer.startTimer()
                }.onAppear {
                    self.countDownTimer.startTimer()
                }
                
            }.onAppear {
                viewmodel.setOtpUseCase(otpUseCase: self.otpUseCase)
            }
            
            if viewmodel.isSuccess {
                NavigationLink(destination: CompleteRegisterScreen(), isActive: .constant(true)) {
                    EmptyView()
                }.hidden()
            }
            
            if viewmodel.isLoading {
                ProgressView("Loading...")
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.Accent))
            }
            
        }
        
    }
}

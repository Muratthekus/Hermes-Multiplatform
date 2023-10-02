//
//  RegisterScreen.swift
//  iosApp
//
//  Created by Murat Kuş on 11.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct RegisterScreen: View {
    @StateObject var viewmodel = RegisterViewModel(otpUseCase: nil)
    
    @State private var emailAddress = ""
    @State private var password = ""
    @State private var isPasswordVisible = false
    @State private var isPasswordLegit = true
    
    private let otpUseCase: OtpUseCase?
    
    init(otpUseCase: OtpUseCase?) {

        self.otpUseCase = otpUseCase
        
    }
    
    var body: some View {
        
        ZStack {
            VStack(alignment: .center) {
                
                Text("Enter your email address")
                    .foregroundColor(Color.OnBackground)
                    .font(.headline)
                
                Text("Please confirm your email addres")
                    .multilineTextAlignment(.center)
                    .padding(.top, 20)
                    .padding(.bottom, 20)
                    .padding(.horizontal, 45)
                    .font(.subheadline)
                    .foregroundColor(Color.OnBackground)
                
                TextField("Email", text: $emailAddress)
                    .frame(height: 50)
                    .padding(.horizontal, 24)
                    .keyboardType(.emailAddress)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                
                SecureInputView("Password", text: $password)
                .padding(.top, 20)
                
                Button(action: {
                    viewmodel.signUpUser(email: self.emailAddress, password: self.password)
                }) {
                    Text("Continue")
                        .font(.headline)
                        .foregroundColor(Color.OnPrimary)
                        .frame(maxWidth: .infinity, minHeight: 52)
                        .background(Color.Accent)
                        .cornerRadius(30)
                }
                .padding(.horizontal, 24)
                .padding(.top, 50)
                Spacer()
            }
            .frame(height: .infinity)
            .onAppear {
                viewmodel.setOtpUseCase(otpUseCase: otpUseCase)
            }
            
            if viewmodel.isSuccess {
                NavigationLink(destination: OtpScreen(), isActive: .constant(true)) {
                    EmptyView()
                }.hidden()
            }
            
            if viewmodel.isLoading {
                ProgressView("Loading...")
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.Accent))
                    .onAppear {
                    }
            }
        }
        
    }
}


@available(iOS 15.0, *)
struct RegisterPreview: PreviewProvider {
    static var previews: some View {
        RegisterScreen(otpUseCase: nil)
    }
}

//private let mModule = OtpUseCaseModule()

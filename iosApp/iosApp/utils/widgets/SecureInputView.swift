//
//  SecureInputView.swift
//  iosApp
//
//  Created by Murat Kuş on 13.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SecureInputView: View {
    
    @Binding private var text: String
    @State private var isSecured: Bool = true
    private var title: String
    
    init(_ title: String, text: Binding<String>) {
        self.title = title
        self._text = text
    }
    
    var body: some View {
        ZStack(alignment: .trailing) {
            Group {
                if isSecured {
                    SecureField(title, text: $text)
                        .padding(.horizontal, 24)
                        .frame(height: 50)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                } else {
                    TextField("Email", text: $text)
                        .frame(height: 50)
                        .padding(.horizontal, 24)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }
            }.padding(.trailing, 32)

            Button(action: {
                isSecured.toggle()
            }) {
                Image(systemName: self.isSecured ? "eye.slash" : "eye")
                    .accentColor(.gray)
            }
        }
    }
}

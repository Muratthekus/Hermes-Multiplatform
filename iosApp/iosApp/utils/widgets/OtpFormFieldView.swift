//
//  OtpModifier.swift
//  iosApp
//
//  Created by Murat Kuş on 12.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct OtpModifer: ViewModifier {
    
    @Binding var pin : String
    
    var textLimt = 1
    
    func limitText(_ upper : Int) {
        if pin.count > upper {
            self.pin = String(pin.prefix(upper))
        }
    }
    
    
    //MARK -> BODY
    func body(content: Content) -> some View {
        content
            .multilineTextAlignment(.center)
            .keyboardType(.numberPad)
            .onReceive(Just(pin)) {_ in limitText(textLimt)}
            .frame(width: 45, height: 45)
            .background(Color.BackgroundColor.cornerRadius(5))
            .background(
                RoundedRectangle(cornerRadius: 5)
                    .stroke(Color.Accent, lineWidth: 2)
            )
    }
}

@available(iOS 15.0, *)
struct OtpFormFieldView: View {
    //MARK -> PROPERTIES
    
    enum FocusPin {
        case  pinOne, pinTwo, pinThree, pinFour, pinFive, pinSix
    }
    
    @FocusState private var pinFocusState : FocusPin?
    @State var pinOne: String = ""
    @State var pinTwo: String = ""
    @State var pinThree: String = ""
    @State var pinFour: String = ""
    @State var pinFive: String = ""
    @State var pinSix: String = "" /*{
        didSet {
            if pinSix != "" {
                self.onFinished(pinOne+pinTwo+pinThree+pinFour+pinFive+pinSix)
            }
        }
    }*/
    
    private var onFinished: (String) -> Void
    
    init(onFinished: @escaping (String) -> Void) {
        self.onFinished = onFinished
    }
    
    var body: some View {
        VStack(alignment: .center ){
            
            Text("Verify your Email Address")
                .foregroundColor(Color.OnBackground)
                .font(.title2)
                .fontWeight(.semibold)
            
            
            Text("Enter 6 digit code we'll text you on Email")
                .foregroundColor(Color.OnBackground)
                .font(.subheadline)
                .fontWeight(.thin)
                .padding(.top, 0.4)
            
            HStack(spacing:15, content: {
                
                TextField("", text: $pinOne)
                    .modifier(OtpModifer(pin:$pinOne))
                    .onChange(of:pinOne){newVal in
                        if (newVal.count == 1) {
                            pinFocusState = .pinTwo
                        }
                    }
                    .focused($pinFocusState, equals: .pinOne)
                
                TextField("", text:  $pinTwo)
                    .modifier(OtpModifer(pin:$pinTwo))
                    .foregroundColor(Color.OnBackground)
                    .onChange(of:pinTwo){newVal in
                        if (newVal.count == 1) {
                            pinFocusState = .pinThree
                        }else {
                            if (newVal.count == 0) {
                                pinFocusState = .pinOne
                            }
                        }
                    }
                    .focused($pinFocusState, equals: .pinTwo)
                
                
                TextField("", text:$pinThree)
                    .modifier(OtpModifer(pin:$pinThree))
                    .foregroundColor(Color.OnBackground)
                    .onChange(of:pinThree){newVal in
                        if (newVal.count == 1) {
                            pinFocusState = .pinFour
                        }else {
                            if (newVal.count == 0) {
                                pinFocusState = .pinTwo
                            }
                        }
                    }
                    .focused($pinFocusState, equals: .pinThree)
                
                
                TextField("", text:$pinFour)
                    .modifier(OtpModifer(pin:$pinFour))
                    .foregroundColor(Color.OnBackground)
                    .onChange(of:pinFour){newVal in
                        if (newVal.count == 1) {
                            pinFocusState = .pinFive
                        }else {
                            if (newVal.count == 0) {
                                pinFocusState = .pinThree
                            }
                        }
                    }
                    .focused($pinFocusState, equals: .pinFour)
                
                TextField("", text:$pinFive)
                    .modifier(OtpModifer(pin:$pinFive))
                    .foregroundColor(Color.OnBackground)
                    .onChange(of:pinFive){newVal in
                        if (newVal.count == 1) {
                            pinFocusState = .pinSix
                        }else {
                            if (newVal.count == 0) {
                                pinFocusState = .pinFour
                            }
                        }
                    }
                    .focused($pinFocusState, equals: .pinFive)
                
                TextField("", text:$pinSix)
                    .modifier(OtpModifer(pin:$pinSix))
                    .foregroundColor(Color.OnBackground)
                    .onChange(of:pinSix){newVal in
                        if (newVal.count == 0) {
                            pinFocusState = .pinFive
                        } else {
                            if (newVal.count == 1) {
                                self.onFinished(pinOne+pinTwo+pinThree+pinFour+pinFive+pinSix)
                            }
                        }
                    }
                    .focused($pinFocusState, equals: .pinSix)
            })
            .padding(.vertical)
            
        }
        
    }
}

@available(iOS 15.0, *)
struct OtpFormField_Previews: PreviewProvider {
    static var previews: some View {
        OtpFormFieldView { code in
            //
        }
    }
}

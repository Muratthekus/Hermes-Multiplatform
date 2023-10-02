//
//  CountDown.swift
//  iosApp
//
//  Created by Murat Kuş on 12.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CountDownView : View {
    @ObservedObject var countDownTimer: CountDownTimer
    private var resendOtp: () -> Void
    
    init(countDownTimer: CountDownTimer, resendOtp: @escaping () -> Void){
        self.countDownTimer = countDownTimer
        self.resendOtp = resendOtp
    }
    
    var body: some View {
        Button(action: {
            self.resendOtp()
        }) {
            Text(countDownTimer.timeStr)
                .font(Font.system(size: 15))
                .foregroundColor(Color.OnBackground)
                .fontWeight(.semibold)
                .onReceive(countDownTimer.timer) { _ in
                    countDownTimer.countDownString()
                }
        }
    }
}
struct CountDown_Previews: PreviewProvider {
    static var previews: some View {
        CountDownView(countDownTimer: CountDownTimer()) {
            //
        }
    }
}


class CountDownTimer: ObservableObject {
    
    //MARK: vars
    var nowDate = Date()
    @Published var timerExpired = false
    @Published var timeStr = ""
    @Published var finishedTimeString = "Resend code"
    @Published var timeRemaining = Constants.COUNTDOWN_TIMER_LENGTH
    var timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
    
    //MARK: functions
    func startTimer() {
        timerExpired = false
        timeRemaining = Constants.COUNTDOWN_TIMER_LENGTH
        self.timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
    }
    
    func stopTimer() {
        timerExpired = true
        self.timer.upstream.connect().cancel()
    }
    
    func countDownString() {
        guard (timeRemaining > 0) else {
            self.timer.upstream.connect().cancel()
            timerExpired = true
            timeStr = finishedTimeString
            return
        }
        
        timeRemaining -= 1
        let (_,m,s) = secondsToHoursMinutesSeconds(timeRemaining)
        timeStr = String(format: "%02d:%02d", m, s)
    }
    
}

func secondsToHoursMinutesSeconds(_ seconds: Int) -> (Int, Int, Int) {
    return (seconds / 3600, (seconds % 3600) / 60, (seconds % 3600) % 60)
}

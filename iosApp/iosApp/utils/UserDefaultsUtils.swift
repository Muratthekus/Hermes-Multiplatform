//
//  UserDefaultsUtils.swift
//  iosApp
//
//  Created by Murat Kuş on 11.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
class UserDefaultsUtils {
static var sharedUserDefaults = UserDefaultsUtils()
    
 func setDarkMode(enable: Bool) {
   let defaults = UserDefaults.standard
   defaults.set(enable, forKey: Constants.DARK_MODE)
 }
 func getDarkMode() -> Bool {
  let defaults = UserDefaults.standard
  return defaults.bool(forKey: Constants.DARK_MODE)
 }
}

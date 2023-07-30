//
//  Colors.swift
//  EducationApp
//
//  Created by n.baklanov on 30.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SwiftDevPackage

//Neutrals
let neutral0 = Color(hex: 0xFFFFFF, alpha: 1.0)
let neutral700 = Color(hex: 0x7C7C7B, alpha: 1.0)

//Gradients
let gradient1 = Color(hex: 0xB4C95E, alpha: 1.0)
let gradient2 = Color(hex: 0xF7F5CC, alpha: 1.0)

let mainVerticalGradient = LinearGradient(
    colors: [gradient1, gradient2],
    startPoint: .top,
    endPoint: .bottom
)

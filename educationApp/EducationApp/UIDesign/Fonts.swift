//
//  Fonts.swift
//  EducationApp
//
//  Created by n.baklanov on 30.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

enum NotoSans {
    case bold(CGFloat)
    case regular(CGFloat)
    
    var fontName: String {
        switch self {
        case .bold:
            return "NotoSans-Bold"
        case .regular:
            return "NotoSans-Bold"
        }
    }
    
    var font: Font {
        switch self {
        case .bold(let fontSize):
            return Font.custom(fontName, size: fontSize)
        case .regular(let fontSize):
            return Font.custom(fontName, size: fontSize)
        }
    }
}

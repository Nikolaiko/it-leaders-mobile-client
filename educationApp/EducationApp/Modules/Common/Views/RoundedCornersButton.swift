//
//  RoundedCornersButton.swift
//  EducationApp
//
//  Created by n.baklanov on 30.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RoundedCornersButton: View {
    var buttonText: String
    var textColor: Color = neutral0
    var backgroundColor: Color = neutral700
    
    var body: some View {
        HStack {
            Spacer()
            Text(buttonText)
                .font(NotoSans.bold(16).font)
                .foregroundColor(textColor)
            Spacer()
        }
        .padding(.vertical, 18)
        .background(backgroundColor)
        .cornerRadius(12)
    }
}

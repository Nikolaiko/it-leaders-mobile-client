//
//  RoundedCornersButtonPreview.swift
//  EducationApp
//
//  Created by n.baklanov on 30.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct RoundedCornersButtonPreviews: PreviewProvider {
    static var previews: some View {
        GeometryReader { geom in
            RoundedCornersButton(
                buttonText: "Зарегистрироваться"
            )
            .padding(.horizontal, 20)
        }
    }
}


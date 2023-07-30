//
//  NotLoggedFeature.swift
//  EducationApp
//
//  Created by n.baklanov on 29.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ComposableArchitecture

struct LoginFeature: ReducerProtocol {
    
    struct State {
        
    }
    
    enum Action {
        case success
    }
    
    func reduce(into state: inout State, action: Action) -> EffectTask<Action> {
        return .none
    }
}

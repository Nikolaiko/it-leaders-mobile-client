//
//  AuthorizedFeature.swift
//  EducationApp
//
//  Created by n.baklanov on 29.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ComposableArchitecture

struct AuthorizedFeature: ReducerProtocol {
    struct State {
        
    }
    
    enum Action {
        case logout
    }
    
    func reduce(into state: inout State, action: Action) -> EffectTask<Action> {
        return .none
    }
}

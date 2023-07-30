//
//  AppMainStore.swift
//  EducationApp
//
//  Created by n.baklanov on 29.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ComposableArchitecture

struct AppFeature: ReducerProtocol {
    enum State {
        case authorized(AuthorizedFeature.State)
        case login(LoginFeature.State)
    }
    
    enum Action: Equatable {
        case authorizedAction(AuthorizedFeature.Action)
        case loginAction(LoginFeature.Action)
    }
    
    var body: some ReducerProtocol<State, Action> {
        Reduce { state, action in
            switch action {
            case .loginAction(.success):
                print("Login success")
                return .none
            case .authorizedAction(.logout):
                print("Logout")
                return .none                        
            case .authorizedAction,.loginAction:
                return .none
            }
        }
        .ifCaseLet(/State.login, action: /Action.loginAction) {
            LoginFeature()
        }
        .ifCaseLet(/State.authorized, action: /Action.authorizedAction) {
            AuthorizedFeature()
        }
    }
}

package com.tahir.validateuser_lib.viewstate

/**
 * Sealed Class with static instance that will be used for setting up statuses.
 *
 */

sealed class SubmitStatus {
    object submitUserData : SubmitStatus()
    object None : SubmitStatus()
}
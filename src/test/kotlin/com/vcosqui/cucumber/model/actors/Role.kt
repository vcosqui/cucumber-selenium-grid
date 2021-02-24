package com.vcosqui.cucumber.model.actors

import com.typesafe.config.ConfigBeanFactory.create
import com.vcosqui.cucumber.configs.Credentials
import com.vcosqui.cucumber.support.extensions.WithConfig

enum class Role(role: String) : WithConfig {

    USER("User");

    override val config = config()

    val creds: Credentials = create(config.getConfig(role), Credentials::class.java)

}
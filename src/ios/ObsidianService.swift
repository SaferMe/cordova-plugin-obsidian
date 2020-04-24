
@objc(ObsidianService) class ObsidianService: CDVPlugin {
    public var commandId: String = "";

    public func data(data: Dictionary<String, Any>) {
       let pluginResult = CDVPluginResult(
         status: CDVCommandStatus_OK,
         messageAs: data
       )
        self.commandDelegate!.send(
             pluginResult,
             callbackId: commandId
           )
        NSLog("success");
    }

    public func error(data: Any) {
        NSLog("error")
    }

  @objc(getTasks:)
  func getTasks(_ command: CDVInvokedUrlCommand) {
    self.commandId = command.callbackId
  }

}




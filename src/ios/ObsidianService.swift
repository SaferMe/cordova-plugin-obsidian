import Obsidian
import ObsidianInterface

@objc(ObsidianService) class ObsidianService: CDVPlugin, SuccessCallback, ErrorCallBack {
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
      let msg = command.arguments[0] as? String ?? ""
      var cordovaL = CordovaImpl(obsidian: Obsidian())
      cordovaL.getTasks(args: ["reportId" : "123"], successCallback: self, errorCallBack: self)

  }

}




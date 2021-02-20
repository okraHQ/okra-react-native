import Foundation

import UIKit
import WebKit

class OkraWebView: UIViewController, WKScriptMessageHandler, WKNavigationDelegate {
    
   
    
    public var baseController : UIViewController?
    
    var linkOptions = [String: String]()
    
    public var dataDictionary: [String:Any]!
    

    @IBOutlet var web: WKWebView!
    
    override func loadView() {
        super.loadView()
        web.configuration.userContentController.add(self, name: "jsMessageHandler")
        web.configuration.userContentController.add(self, name: "jsErrorMessageHandler")
        web.configuration.userContentController.add(self, name: "jsCloseMessageHandler")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        let web:UIWebView = UIWebView(frame: CGRectMake(0, 0, UIScreen.mainScreen().bounds.width, UIScreen.mainScreen().bounds.height))
//        web.loadRequest(NSURLRequest(URL: formatUrl()))
//        web.delegate = self;
        let web = WKWebView()
        web.frame  = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height)
        let request = URLRequest(url: formatUrl())
        web.load(request)
        web.navigationDelegate = self
    }
    
    func webView(_ webView: WKWebView, didFinish  navigation: WKNavigation!)
    {
        if let theJSONData = try?  JSONSerialization.data(
            withJSONObject: dataDictionary ?? [:],
            options: .sortedKeys
              ),
              let json = String(data: theJSONData,
                                       encoding: String.Encoding.utf8) {
            web.evaluateJavaScript("openOkraWidget('"+json+"')", completionHandler: { (object,error) in})
            }
    }

    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        if(message.name == "jsMessageHandler") {
              NotificationCenter.default.post(name: Notification.Name(rawValue: "okra.onSuccess"), object: ["data": message.body as! String])
            
            
        }else if(message.name == "jsErrorMessageHandler"){
             NotificationCenter.default.post(name: Notification.Name(rawValue: "okra.onError"), object: ["data": message.body as! String])
            
        }else if(message.name == "jsCloseMessageHandler"){
             NotificationCenter.default.post(name: Notification.Name(rawValue: "okra.onClose"), object: ["data": message.body as! String])
        }
        switchToPreviousPage();
    }
    
    func switchToPreviousPage(){
        self.dismiss(animated: true, completion: nil)
    }
    
    func formatUrl() -> URL{
        var urlComponents = URLComponents()
        urlComponents.scheme = "https"
        urlComponents.host = "mobile.okra.ng"
        urlComponents.path = "/"
        return urlComponents.url!
    }
}

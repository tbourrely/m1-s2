//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml.Serialization;

// 
// This source code was auto-generated by Web Services Description Language Utility
//Mono Framework v4.0.30319.42000
//


/// CodeRemarks
[System.CodeDom.Compiler.GeneratedCodeAttribute("wsdl", "0.0.0.0")]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Web.Services.WebServiceBindingAttribute(Name="HelloWorldServicePortBinding", Namespace="http://tp1.soap.tbo/")]
public partial class HelloWorldService : System.Web.Services.Protocols.SoapHttpClientProtocol {
    
    private System.Threading.SendOrPostCallback helloWorldOperationCompleted;
    
    /// CodeRemarks
    public HelloWorldService() {
        this.Url = "http://localhost:8080/SOAPTP1/HelloWorldService";
    }
    
    /// CodeRemarks
    public event helloWorldCompletedEventHandler helloWorldCompleted;
    
    /// CodeRemarks
    [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://tp1.soap.tbo/", ResponseNamespace="http://tp1.soap.tbo/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
    [return: System.Xml.Serialization.XmlElementAttribute("response", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public string helloWorld([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] string name) {
        object[] results = this.Invoke("helloWorld", new object[] {
                    name});
        return ((string)(results[0]));
    }
    
    /// CodeRemarks
    public void helloWorldAsync(string name) {
        this.helloWorldAsync(name, null);
    }
    
    /// CodeRemarks
    public void helloWorldAsync(string name, object userState) {
        if ((this.helloWorldOperationCompleted == null)) {
            this.helloWorldOperationCompleted = new System.Threading.SendOrPostCallback(this.OnhelloWorldOperationCompleted);
        }
        this.InvokeAsync("helloWorld", new object[] {
                    name}, this.helloWorldOperationCompleted, userState);
    }
    
    private void OnhelloWorldOperationCompleted(object arg) {
        if ((this.helloWorldCompleted != null)) {
            System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
            this.helloWorldCompleted(this, new helloWorldCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
        }
    }
    
    /// CodeRemarks
    public new void CancelAsync(object userState) {
        base.CancelAsync(userState);
    }
}

/// CodeRemarks
[System.CodeDom.Compiler.GeneratedCodeAttribute("wsdl", "0.0.0.0")]
public delegate void helloWorldCompletedEventHandler(object sender, helloWorldCompletedEventArgs e);

/// CodeRemarks
[System.CodeDom.Compiler.GeneratedCodeAttribute("wsdl", "0.0.0.0")]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
public partial class helloWorldCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
    
    private object[] results;
    
    internal helloWorldCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
            base(exception, cancelled, userState) {
        this.results = results;
    }
    
    /// CodeRemarks
    public string Result {
        get {
            this.RaiseExceptionIfNecessary();
            return ((string)(this.results[0]));
        }
    }
}

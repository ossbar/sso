/*
 *    @author: FusionCharts Team (SB)
 *    @description: FusionCharts Export Component
 *
 *    @publish: 2009-04-05
 *    @version: 1.0.1 (build 351)
 */

/*
 *  Internal ChangeLog / Version History:
 *  ============================
 *
 *  0.1 - 27/01/2009
 *  Just created comments!
 *
 *  1.0 - 20/01/2009
 *  First release!
 *
 *  1.0.1 - 17/03/2009
 *  Fixed product name references
 *  Added internal function to further streamLine data-flow
 *
 *  Notes:
 *  ======
 *
 *  #Architectural Overview:
 *  1. FC_ExportDataReady is called by individual charts and ASCII data with
 *     other meta informations (now on referred as StreamObject) are sent to
 *     the "relayStream" method.
 *  2. Relay method does some validations and checks whether it has a valid
 *     'ExportComponent SWF HTMLEmbed Object' ready or not.
 *  3. In case ExportComponent is ready, it relays the stream to the object,
 *     Otherwise it enqueues the object in the streamRack variable.
 *  4. FC_ExportComponentReady is called when individual components are ready
 *     to accept data. When it attains readiness, it checks whether there is
 *     already any StreamObject queued up in STEP 4. If yes, it Flushes the
 *     entire queue of StreamObjects.
 *
 *  # Error number convention:
 *  1yymmddhhmm
 *
 *  # Why have we not made aliases of FC_ExportDataReady and
 *  FC_ExportComponentReady?
 *  It was observed that in general, when we mad alias, it actually made a
 *  shallow copy
 *
 *  # Some programming principles that we have followed...
 *  Firstly, our goal was (1) Extremely optimized code, both spatially as
 *  well as temporally. (2) Light prototype definition... allowing for lesser
 *  overhead per new object. (3) Fewer try-catch blocks. (4) Cross-browser
 *  compatible. (5) Co-exist with almost all other JS frameworks. 
 */


//  Check whether FusionCharts main object is already declared or not.
//  Debug Info: In case you get an error, make sure -
//  1. FusionChartsExportComponent.js (this file) is included AFTER the main
//     FusionCharts.js file is included.
//  2. If the parent script has any modifications or errors, the main object may
//     remain undefined
if(typeof infosoftglobal == 'undefined'
    || typeof infosoftglobal.FusionCharts == 'undefined')
{
    alert('FusionChartsExport: object::FusionCharts was not found.'
        + ' Verify script inclusions.', 10901271400);
    throw 'FusionChartsExportComponentFatalError::10901271400';
}


// =============================================================================
// == Public Functions and Variables ==/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
// =============================================================================
// Users are recommended NOT to perform any editing beyond this point.

/**
 *  Create FusionChartsExportObject class and map an easy to use variable to
 *  this as this is too long of a name to be used as a workset
 *  @var FusionChartsExportObject is set as an easy-access alias for the global
 *  namespace.
 *  @var _FCEO is another alias for easy internal JS space-saving coding.
 */
infosoftglobal.FusionChartsExportObject = function()
{
    // initialize component constructor routines (that has been declared
    // separately)
    _FCEO.initializeComponent(this, arguments);
};
var FusionChartsExportObject = infosoftglobal.FusionChartsExportObject;
var _FCEO = infosoftglobal.FusionChartsExportObject;

/**
 *  Set of default values used as parameters while setting various properties
 *  of individual instances of the ExportObject
 */
_FCEO.defaultParameters = {
    id: undefined, // Please do not change
    swfLocation: undefined, // you may set it to default value for your site
    componentAttributes: {
        width: '120',  // width of the SWF component (required)
        height: '40',  // height of the SWF component (required)
        bgColor: '',   // bgColor of the SWF
        modalExport: '0', // whether the export process is modal or not
        strictTrigger: '0'  // whether the handlerId is overrideable for charts
    },
    exportAttributes: { },
    sourceCharts: [],
    debugMode: false, // set it to false
    outerHTML: '' // modify this to have a default text when loading fails
};

/**
 *  Flag that marks whether to detect flash version or not
 */
_FCEO.checkFlashVersion = false;

/**
 *  Method called by ExportComponent SWF when it is loaded and ready to take in
 *  StreamObjects.
 *  @param strCompId string: The DOM ID of the export component that has
 *         attained the required readiness level.
 */
var FC_ExportComponentReady = function(strCompId)
{
    // Clear the shelf if there are any StreamObjects already in the rack.
    _FCEO.flushRack(strCompId);

    // Notify the Export Component that JS is loaded and functioning properly.
    return true;
};


/**
 *  Call method to the relayStream function as Flash cannot invoke functions
 *  within objects.
 *  @param streamObj object: Pre-formatted property-list sent from individual
 *         chart SWFs.
 */
var FC_ExportDataReady = function(streamObj)
{
    // Call the relayer function to relay this object to its corresponding
    // export component.
	return _FCEO.relayStream(streamObj) 

};


/**
 *  Function that is called to perform an innerHTML append of the SWF HTML code
 *  to a specified DOM element. It refers to private render() function.
 *  @param containerNode variant: The Dom ID string or HTMLDOMElement of whose
 *         innerHTML will be replaced by the swfcode
 */
_FCEO.prototype.Render = function(containerNode)
{
	return this.render(containerNode);
};

/**
 *  Initiates exportChart sequence on all the charts provided in the
 *  sourceCharts array
 *  @return array
 */
_FCEO.prototype.BeginExport = function()
{
    // call the batchExport() function with the source charts as parameter
    return _FCEO.batchExport(this, this.sourceCharts);
};

/**
 * Initiates exportChart sequence on ALL FusionCharts within the current page
 * @return array
 */
_FCEO.prototype.BeginExportAll = function()
{
    // Internal Algorithm:
    // 1. Get all object and embed elements
    // 2. Call batchExport with above charts

    // call the batchExport() function with the all charts as parameter
    return _FCEO.batchExport( this, _FCEO.probeCharts() );
};


/**
 *  Returns the outerHTML of the SWF component.
 *  @return string
 */
_FCEO.prototype.GetOuterHTML = function()
{
    this.update();
    return this.outerHTML;
};


/**
 *  Function that is called to perform an innerHTML append of the SWF HTML code
 *  to a specified DOM element
 *  @param containerNode variant: The Dom ID string or HTMLDOMElement of whose
 *         innerHTML will be replaced by the swfcode
 */
_FCEO.prototype.render = function(containerNode)
{
    // Internal Algorithm:
    // 1. Locate and load the containerNode.
    // 2. In case, container is not found, raise error.
    // 3. If object is found then replace innerHTML with the outerHTML
    //    property.
    var obj = _FCEO.L.get(containerNode);

	// in case the container division is not found, raise error
    if(!obj || !_FCEO.L.isObj(obj.innerHTML, 'string')) {
        this.trace(_FCEO.R.errContainer, 10902091233);
        return false;
    }

    // put swf html inside the container DOM after updating the swfhtml
	this.update();
	
    try {
        this.srcObj.render(obj);
    }
    catch (err) {
        this.trace(_FCEO.R.errFusion + '\n' + err, 10902101448);
    }

    // notify successful render
    return true;
};

/**
 *  This function updates the FusionCharts object within the codes as and when
 *  users update the UIProperties
 *  @throws FusionChartsComponentException is raised when due to some god
 *          forsaken reason, the internal FusionCharts object (this.srcObj)
 *          cannot be updated with what data the trigger has raised.
 */
_FCEO.prototype.update = function()
{
	// Call the static updateComponent function with "this" object as
	// reference.
	_FCEO.updateComponent(this);
};

/**
 *  Show debug messages to users in case debugMode is enabled.
 *  @param typ string: Debug Message
 *  @param num integer: Error Number
 */
_FCEO.prototype.trace = function(typ, num) {
    // Call the Global/Static 'trace' method of _FCEO object
    _FCEO.trace(typ, num, this.debugMode);
};


/**
 *  Override the toString() to return valid component name when alerted.
 *  @return string This export component's registration name
 */
_FCEO.prototype.toString = function() {
    return '[object ' + _FCEO.R.resSig + ' #' + this.id +' ]';
};

// =============================================================================
// == Private Functions and Variables ==/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
// =============================================================================
// Users are NOT to perform any editing at this point.


/**
 *  A Rack-type data-structure that will hold all the StreamObjects until their
 *  respective components are loaded. The data structure here looks like:
 *  { ExportComponentId_1 : [StreamObject1, StreamObject2, ...], ... }
 */
_FCEO.streamRack = { };

/**
 *  The number of modal charts that are pending to be completely captured (m)
 *  and also the total counter
 */
_FCEO.flags = {c:0};


/**
 *  Get the adobe player version from main JS
 */

_FCEO.playerVersion = {major: 0, minor: 0, rev: 0};
try {
	_FCEO.playerVersion = infosoftglobal.FusionChartsUtil.getPlayerVersion();
}
catch (err) {
	_FCEO.trace('FusionChartsExport: object::Flash Player DetectionError.\n' +
        err, 10902182119);
}

/**
 *  This function relays a particular StreamObject to its corresponding
 *  export component (as specified within the stream parameters.
 *  @exception StreamError
 *  @param streamObj object: The stream object that has to be relayed to an
 *         export component
 */
_FCEO.relayStream = function(streamObj)
{
    // Internal Algorithm:
    // 1. Check whether the corresponding export component is ready
    //    or not.
	// 2. Bubble external event
    // 3. If ready, then perform pushData() routines
    // 4. If component is not ready, push the streamObject to the shelf.
    // 5. Tag the stream with streamMeta information

    // First get the export component's id from the stream object and raise
    // error in case the handler id is not determinable.
    var sHandlerId = _FCEO.probeHandler(streamObj);
    if(sHandlerId == null){
        _FCEO.trace(_FCEO.R.errStream, 10902041759);
    }

    // check whether the stream has been tagged for its export order.
    if( !_FCEO.L.isObj(streamObj.parameters.exportParameters) ) {
        streamObj.parameters.exportParameters = {};
    }
    if(!_FCEO.L.isObj(streamObj.parameters.exportParameters.triggerOrdinal,
        'number'))
    {
        streamObj.parameters.exportParameters.triggerOrdinal = _FCEO.flags.c++;
    }
	
	// bubble event ... this is done as timeout, so that this script doesn't get 
	// hung when the external script is heavy
	setTimeout(function() { 
		try { FC_ExportReady(streamObj.meta.DOMId); } catch(err) {} } , 0);
	
	// check whether the component is ready or not. if ready then push data,
    // else shove the data to shelf!
    if( _FCEO.probeComponent(sHandlerId) ) {
        _FCEO.pushStream(sHandlerId, [streamObj]);
        return true;
    }

    // At this point, we know that the handler is not ready, so we shove that
    // StreamObject to rack. Check if a shelf needs to be created in streamRack
    // and correspondingly add this stream to the shelf rack.
    if(!_FCEO.streamRack[sHandlerId]) {
        _FCEO.streamRack[sHandlerId] = [];
    }
    _FCEO.streamRack[sHandlerId].push(streamObj); // add stream to shelf array
	return true;
};

/**
 *  This function relays all items within the streamRack for a particular
 *  id to its corresponding exporter swf
 *  @param sHandlerId string: DOM ID of the SWF Exporter Component.
 */
_FCEO.flushRack = function(sHandlerId)
{
    // Internal Algorithm:
    // 1. Check if SWF Export Component is ready. if not raise error
    // 2. Send all StreamObjects from Rack to SWF Component
    // 3. Clear Rack.

    // Validate the parameter
    sHandlerId = _FCEO.probeHandler(sHandlerId);
    if(sHandlerId == null) {
        _FCEO.trace(_FCEO.R.errComponent, 10902041939);
    }

    // Check whether the component is ready or not. If ready then clear
    // everything from rack, else raise error
    if( !_FCEO.probeComponent(sHandlerId) ) {
        _FCEO.trace(_FCEO.R.errComponent, 10902041917);
    }

    // Component is found and ready, so we should now check if data exists in
    // Rack. if not, exit.
    if(!_FCEO.streamRack[sHandlerId]) {
        return;
    }

    // Shelf exists, so simply push the array item.
    _FCEO.pushStream(sHandlerId, _FCEO.streamRack[sHandlerId]);

    // data is pushed, now reset array
    _FCEO.streamRack[sHandlerId] = [];

};

/**
 *  This function simply does the "PushData" routine and any other routines
 *  prior to that
 *  @sHandlerId string: The handler ID of the object
 *  @objStream object: The stream to be pushed.
 */

_FCEO.pushStream = function(sHandlerId, objStream)
{
	return _FCEO.L.get(sHandlerId).pushData(objStream);
};

/**
 *  This function checks whether a particular export component is ready to
 *  receive data or not.
 *  @param sHandlerID string: The DOM ID that refers to a particular export
 *         component.
 *  @return boolean
 */
_FCEO.probeComponent = function(sHandlerID)
{
    // Internal Algorithm:
    // 1. Validate parameter(s)
    // 2. Check if the DOM ID returns a valid object.
    // 3. Query object for readiness level.

    // Validate parameter.
    if(!sHandlerID) {
        return false;
    }

    // Determine the export component by identifying whether the src parameter
    // is the string DOM id itself or the entire StreamObject.
    var exOb = _FCEO.L.get( sHandlerID );

    // check whether the unearthed object is loaded and if loaded whether it
    // is ready to accept data or not.
    return exOb ? exOb.isReady != null && exOb.isReady() == true : false;
};


/**
 *  This function returns the export component id by parsing the parameter passed
 *  @param vSource variant: The StreamObject or String from where to locate
 *         the export component SWF element
 *  @return boolean
 */
_FCEO.probeHandler = function(vSource)
{
    // Internal Algorithm:
    // 1. Validate parameter(s)
    // 2. Determine the export component's DOM ID from the parameter

    // Validate parameter.
    if(!vSource) { return null; }

    // Determine the export component id by identifying whether the src
    // parameter is the string DOM id itself or the entire StreamObject.
    return _FCEO.L.isString(vSource) ? vSource : (vSource.parameters != null
        && _FCEO.L.isString(vSource.parameters.exportHandler) ) ?
        vSource.parameters.exportHandler : null;
};


/**
 *  Parses DOM tree for all existing FusionCharts.
 *  Note: supported for FusionCharts with release 3.1 or higher.
 */
_FCEO.probeCharts = function()
{
    // Internal Algorithm:
    // 1. Parse through objects and accumulate valid FusionCharts
    // 2. Parse through embeds and accumulate as above, rejecting nested
    //    object/embeds

    // initialize token variable and accumulator variable
    var tok, toks, acc = [];

    // Pass 1: run though all objects
    toks = _FCEO.L.tags('object');
    for(var i = 0; i < toks.length; i++) {
        tok = toks[i];
        if(_FCEO.probeChart(tok) )  {
            acc.push(tok);
        }
    }

    // Pass 2: run though all embeds
    toks = _FCEO.L.tags('embed');
    for(i = 0; i < toks.length; i++) {
        tok = toks[i];
        if( tok.parentNode != undefined && tok.parentNode.tagName == 'object') {
            continue;
        }
        if(_FCEO.probeChart(tok) )  {
            acc.push(tok);
        }
    }

    return acc;
};


/**
 *  Determines whether a particular HTML DOM element is a FusionCharts or not.
 *  @param obj object: HTMLElement
 *  @return boolean
 */
_FCEO.probeChart = function(obj)
{
    // in case chart/object is not capable of signing!
    if( obj.signature == undefined ) { return false; }

    // fetch the signature.
    var sig = obj.signature();
    return ( _FCEO.L.isString(sig) && sig.indexOf(_FCEO.R.resChartSig) == 0 );
};


/**
 *  This function, sets all the default parameters and related block variables
 *  for the new instance of ExportObject and also processes its construction
 *  parameters
 *  @param obj FusionChartsExportObject: usually a "this" value sent by the
 *         constructor.
 *  @param args Array: usually the "arguments" array passed on by the
 *         constructor.
 */
_FCEO.initializeComponent = function(obj, args)
{
    // Internal Algorithm:
    // 1. Check whether the argument is in param mode or JSON mode
    // 2. Validate arguments pertinant to FCExport SWF object creation only.
	//    (also check player version)
    // 3. Create new srcObj (FusionCharts) out of the args


    // Checking compulsory arguments. Raise error if the compulsory arguments
    // are not present. FIRST ARGS COMPUSLORY FOR JSON/PARAM form
    if(!args || !args.length || args.length < 1 ) {
        _FCEO.trace(_FCEO.R.errArgs, 10902061902);
    }

    // test and validate if args is a JSON format entry.
    var isJSON = !_FCEO.L.isString(args[0]) && args.length == 1;

    // if entry is not json format then FIRST TWO ARGS are compulsory
    if(!isJSON && args.length < 2 ) {
        _FCEO.trace(_FCEO.R.errArgs, 10902091732);
    }

    // initializeComponent all internal properties from the defaulSettings definition
    // also override with args array in case anything is provided there.
    var i = 0;
    for(var params in _FCEO.defaultParameters) {
        obj[params] = (isJSON ? args[0][params] : args[i++]) ||
            _FCEO.defaultParameters[params];
    }

    // final precautionary validation of two compulsory parameters
    if(!_FCEO.L.isString(obj.id) || !_FCEO.L.isString(obj.swfLocation)) {
        obj.trace(_FCEO.R.errArgs, 10902091820);
    }

	// check flash version
	if( _FCEO.playerVersion.major < 10 ) {
		if(_FCEO.checkFlashVersion && 
		   !(obj.debugMode || _FCEO.defaultParameters.debugMode)) {
			alert(_FCEO.R.msgNoPlayer);
		}
		obj.trace(_FCEO.R.errPlayer, 10902182131);
	}
	
    // create a new FusionCharts object from the arguments. 
    obj.srcObj = new FusionCharts(obj.swfLocation, obj.id, 0, 0, 
		obj.debugMode);

};

/**
 *  Updates the SWF object and other related fields of a component provided in
 *  the parameter
 *  @param obj object: And instance of FusionChartsExportObject, usually 'this'
 */
_FCEO.updateComponent = function(obj) {
    // Internal Algorithm:
    // 1. check if FusionCharts object is created.
    // 2. update componentAttributes and related things in case hook is provided

    // create new FusionCharts object if one is not already created
    if(!obj.srcObj) {
		obj.trace(_FCEO.R.errFusion, 10902220400);
    }

    // set initial and other related values by synchronizing component attributes
    _FCEO.L.sync(obj.componentAttributes, obj.srcObj.variables);

    // create a function that does the returning of  a default value in case one
    // is not provided in componentAttributes
	var prm;
    var getParam = function(val) {
		return !_FCEO.L.isObj(obj.srcObj.variables[val], 'undefined') ? 
			obj.srcObj.variables[val] : _FCEO.defaultParameters.componentAttributes[val];
    };

    try {
		// append mandatory flashVars
		obj.srcObj.variables['generator'] = _FCEO.R.resSig;
	
		// send mandatory width height, in case they are changed by user after
		// construction and other parameters.
		prm = getParam('width');
		if(prm != undefined) { 
			obj.srcObj.setAttribute('width', prm);
			obj.srcObj.addVariable('width', prm);
		}
		
		prm = getParam('height');
		if(prm != undefined) { 
			obj.srcObj.setAttribute('height', prm); 
			obj.srcObj.addVariable('height', prm); 
		}
	
		prm = getParam('bgColor');
		if(prm != undefined) { obj.srcObj.addParam('bgColor', prm); }
	
		// generate html for the object
		obj.outerHTML = obj.srcObj.getSWFHTML();
    }
    catch (err) {
        this.trace(_FCEO.R.errFusion + '' + err, 10902091318);
    }

};


/**
 *  Initiates exportChart() command on an array of FusionCharts
 *  @param obj object: FusionChartsExportComponent that will invoke batch
 *  @param src array: The source charts that will be used to run batch on
 *  @return array
 */
_FCEO.batchExport = function(obj, src)
{
    // Internal Algorithm:
    // 1. Validate Export Parameters
    // 2. Create batch related meta information in exportAttributes
    // 3. Call exportChart on sourceCharts

    // check whether sourceCharts is a valid array
    if( !(src instanceof Array) ) {
        obj.trace(_FCEO.R.errArgs, 10902091853);
        return [];
    }

    // accumulator variable to store DOM ids of successfully exported charts
    // and the present mutex state to be recorded
    var accArr = [], mRef = _FCEO.flags.c;

    // append the state changes to the counter
    _FCEO.flags.c += src.length;

    // create iteration specific functions. This is done so that unnecessary
    // (if) blocks can be removed from within the loop below.
    var doStrictTrigger = parseInt(obj.componentAttributes.strictTrigger || 0) == 0 ?
        function(expAttr) {return expAttr;} :
        function(expAttr) {expAttr.handlerId = obj.id; return expAttr; };

    // iterate through the arrays and raise export command
    for(var i = 0; i < src.length; i++) {

        // Get the chart object and in case it is not found proceed to next
        // chart object.
        var cObj = _FCEO.L.get(src[i]);
        if( cObj == null) {
            obj.trace(_FCEO.R.errFusion, 10902092026);
            continue;
        }

        // prepare export parameter that would notify the component about the
        // order of the charts.
        // create parameter if not already existing in object stream
        if( !_FCEO.L.isObj(obj.exportAttributes.exportParameters) ) {
            obj.exportAttributes.exportParameters = {};
        }
        obj.exportAttributes.exportParameters.triggerOrdinal = mRef++;
        obj.exportAttributes = doStrictTrigger(obj.exportAttributes);

        // initiate exportChart() command within an if block so that any
        // external error can be trapped with ease
        if(cObj && cObj.exportChart) {
            cObj.exportChart(obj.exportAttributes);
            accArr.push(cObj.id);
        }
        else {
            obj.trace(_FCEO.R.errFusion + '\nRef: ' + cObj.id, 10902092036);
        }
    }

    // notify successful export
    return accArr;
};


/**
 *  Override the toString() to return valid component name when alerted.
 *  @return string This export component's registration name
 */
_FCEO.toString = function() {
    return '[object ' + _FCEO.R.resSig +']';
};


/**
 *  Raise an error in form of message or JS error. This error is suppressed in
 *  case debugMode is disabled. the debugMode status is bubbled up from
 *  _FCEO.defaultSettings
 */
_FCEO.trace = function(typ, num, verb) {
    if( verb || _FCEO.defaultParameters.debugMode ) {
        _FCEO.L.raise(typ, num);
    }
};

// =============================================================================
// == Resources ==/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
// =============================================================================
// Users may edit certain elements within this section to meet their needs.
/**
 *  String resources separated from JS. For localization and js compression.
 */
_FCEO.R = {
    errStream: 'Inbound stream missing or stream integrity failure.',
    errComponent: 'Export component missing or authentication failure.',
    errArgs: 'Invalid arguments or parameters out of bounds exception.',
    errContainer: 'Invalid container id or HTMLNode missing from DOM.',
    errFusion: 'Error with internal FusionCharts object. Review parameters.',
    errPlayer: 'Incompatible Flash player or Flash player not installed. Flash Player 10 (or above) is needed for Export Component.',
	msgNoPlayer: 'This page contains elements that require the latest version of Flash Player plugin.',
    resSig: 'FusionChartsExportComponent',
    resChartSig: 'FusionCharts/'
};

// =============================================================================
// == Library ==/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
// =============================================================================
// Users are NOT to perform any editing at this point.

/**
 *  Common runtime/library snippet functions that will be repeatedly and
 *  extensively used in this script. All cross-browser functions should be
 *  implemented hers so that debugging is faster.
 */
_FCEO.L = {

    isIE: navigator.appName == "Microsoft Internet Explorer",
    isFF: document.getElementById && !document.all,

    /**
     *  Shorthand reference to document.getElementByTagName() function to ease
     *  of repeated use within JS codes.
     *  @param nam string: the node tag name that is to be looked up within DOM
     *  @param obj object: optional object in which to look for the tag.
     *         default is 'document'
     *  @return object: HTMLElementCollection or DOM nodes matching the tag in
     *          parameter
     */
    tags: function(nam, obj) {
        if(!_FCEO.L.isString(nam)) return [];
        return (obj || document).getElementsByTagName(nam);
    },

    /**
     *  Shorthand reference to document.getElementById() function to ease of
     *  repeated use within JS codes.
     *  @param id string: the id that is to be looked up within DOM
     *  @param obj object: optional object in which to look for the id.
     *         default is 'document'
     *  @return object: HTMLElement or DOM nodes matching the id in parameter
     */
    get: function(id, obj) {
        if(!_FCEO.L.isString(id)) return id;
        return (obj || document).getElementById(id);
    },

    /**
     *  Returns a new DOM node as specified in tag names. Also, any subsequent
     *  parameter with string 'key=value' pair will be set as attributes to
     *  nodes
     *  @param tag string: the DOM strings
     */
    getNew: function(tag)
    {
        var el = document.createElement(tag), arg;
        for(var i=1; c<arguments.length; i++)
        {
            arg = arguments[i].split('=');
            el.setAttribute(arg[0], arg[1]);
        }
        return el;
    },
	
	/**
	 *  Syncs two property-lists
	 *  @param src object: Source property-list
	 *  @param tgt object: Target property-list
	 */
	sync: function(src, tgt)
	{
		for(var itm in src) {
			tgt[itm] = src[itm];
		}
	},

    /**
     *  Determines whether an object is of type string or not.
     *  @param obj object: The object whose type is to be verified
     *  @return boolean
     */
    isString: function(obj) {
        return typeof(obj) == 'string';
    },

    /**
     *  Determines whether an object is of type 'object' or not.
     *  @param obj object: The object whose type is to be verified
     *  @param cmp object: The object name to compare with
     *  @return boolean
     */
    isObj: function(obj, cmp) {
        return typeof(obj) == (cmp ? cmp : 'object');
    },

    /**
     *  Function that determines whether to show alert message or throw
     *  exception.
     *  @param msg string: Error Message to show
     *  @param num integer: Error number (follow a convention)
     *  @param oid integer: Error source object id
     */
    raise: function(msg, num, oid) {
		oid = oid ? ('# ' + oid) : '';
        msg = '[object ' + _FCEO.R.resSig + oid + ']\n\nError ' + num + '.\n' +
            msg + '\n\nRefer to documentation.';
        alert(msg); throw msg;
    }
};
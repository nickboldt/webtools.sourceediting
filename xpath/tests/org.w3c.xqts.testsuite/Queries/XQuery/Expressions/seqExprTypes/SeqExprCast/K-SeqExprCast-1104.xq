(:*******************************************************:)
(: Test: K-SeqExprCast-1104                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gMonthDay to xs:anyURI isn't allowed. :)
(:*******************************************************:)
xs:gMonthDay("--11-13") cast as xs:anyURI
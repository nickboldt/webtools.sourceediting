(:*******************************************************:)
(: Test: K-SeqExprCast-1469                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'castable as' involving xs:QName as source type and xs:QName as target type should always evaluate to true. :)
(:*******************************************************:)
xs:QName("ncname") castable as xs:QName
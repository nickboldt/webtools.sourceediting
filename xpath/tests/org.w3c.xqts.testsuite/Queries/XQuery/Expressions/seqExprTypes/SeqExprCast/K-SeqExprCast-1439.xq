(:*******************************************************:)
(: Test: K-SeqExprCast-1439                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:duration isn't allowed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:duration
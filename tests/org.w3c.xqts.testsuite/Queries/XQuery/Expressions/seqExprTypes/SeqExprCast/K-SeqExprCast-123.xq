(:*******************************************************:)
(: Test: K-SeqExprCast-123                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An invalid lexical representation for xs:base64Binary. :)
(:*******************************************************:)
xs:string(xs:base64Binary("aabb=d=="))
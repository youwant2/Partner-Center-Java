// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license. See the LICENSE file in the project root for full license information.

package com.microsoft.store.partnercenter.analytics;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.analytics.CustomerLicensesUsageInsights;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Implements the operations on customer licenses usage insights collection.
 */
public class CustomerLicensesUsageInsightsCollectionOperations
    extends BasePartnerComponentString
    implements ICustomerLicensesUsageInsightsCollection
{
    /**
     * Initializes a new instance of the CustomerLicensesUsageInsightsCollectionOperations class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The identifier for the customer.
     */
    public CustomerLicensesUsageInsightsCollectionOperations(IPartner rootPartnerOperations, String customerId)
    {
        super(rootPartnerOperations, customerId);

        if (StringHelper.isNullOrWhiteSpace(customerId))
        {
            throw new IllegalArgumentException("customerId must be set.");
        }
    }

    /**
     * Retrieves the collection of customer's licenses' deployment insights.
     * 
     * @return Collection of customer licenses deployment insights.
     */
    @Override
    public ResourceCollection<CustomerLicensesUsageInsights> get()
    {
        return this.getPartner().getServiceClient().get(
            this.getPartner(),
            new TypeReference<ResourceCollection<CustomerLicensesUsageInsights>>(){},
            MessageFormat.format(
                PartnerService.getInstance().getConfiguration().getApis().get("CustomerLicensesUsageInsights").getPath(),
                this.getContext()));
    }
}
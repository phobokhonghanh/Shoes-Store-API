package fit.edu.tmdt.shoes_store_api.service.impl;

import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.entities.Image;
import fit.edu.tmdt.shoes_store_api.entities.Product;
import fit.edu.tmdt.shoes_store_api.entities.Size;
import fit.edu.tmdt.shoes_store_api.repository.ImageRepo;
import fit.edu.tmdt.shoes_store_api.repository.ProductRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.repository.Specification.ProductSpecification;
import fit.edu.tmdt.shoes_store_api.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ImplUtil implUtil;
    @Autowired
    private ConvertBase convertBase;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private SupportService supportService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;

    @Override
    public Page<ProductResponse> getAll(Integer pageNo, Integer pageSize, String search, boolean sort, String filter, Integer brand, Integer type, String sex, boolean active) {
        Sort sorting = sort ? Sort.by(Sort.Direction.ASC, filter) : Sort.by(Sort.Direction.DESC, filter);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sorting);
        Specification<Product> spec = Specification.where(ProductSpecification.containsTextInField(search, "name"))
                .and(ProductSpecification.joinAttribute(brand, "brand"))
                .and(ProductSpecification.joinAttribute(type, "type"));
        if (sex != "") {
            spec = spec.and(ProductSpecification.joinAttribute(sex, "sex"));
        }
        if (active) {
            spec = spec.and(ProductSpecification.joinAttribute(Status.UNLOCK, "status"));
        }
        Page<Product> productsEntity = productRepo.findAll(spec, pageable);
        List<ProductResponse> productsDTO = convertBase.toListConvert(productsEntity.getContent(), ProductResponse.class);
        return new PageImpl<>(productsDTO, pageable, productsEntity.getTotalElements());
    }

    @Override
    public ProductResponse getById(Long id) {
        Optional<Product> optional = productRepo.findById(id);
        return implUtil.getOptional(optional, ProductResponse.class);
    }

    @Override
    public ProductResponse create(ProductDTO productDTO) {
        Product product = productRepo.save(convertBase.convert(productDTO, Product.class));
        for (Size s : product.getSizes()) {
            s.setProduct(product);
            sizeRepo.save(s);
        }
        for (Image i : product.getImages()) {
            i.setProduct(product);
            imageRepo.save(i);
        }
        return convertBase.convert(product, ProductResponse.class);
    }

    @Override
    public ProductResponse update(ProductDTO productDTO) {
        Product product = productRepo.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Product productConvert = convertBase.convert(productDTO, Product.class);

        implUtil.updateFieldIfNotNull(productConvert.getName(), product::setName);
        implUtil.updateFieldIfNotNull(productConvert.getCode(), product::setCode);
        implUtil.updateFieldIfNotNull(productConvert.getShortDescription(), product::setShortDescription);
        implUtil.updateFieldIfNotNull(productConvert.getDescription(), product::setDescription);

        if (productDTO.getStatus() != null) {
            product.setStatus(supportService.getSupport(productDTO.getStatus().getId()));
        }
        if (productDTO.getSex() != null) {
            product.setSex(supportService.getSupport(productDTO.getSex().getId()));
        }
        if (productDTO.getType() != null) {
            product.setType(typeService.getType(productDTO.getType().getId()));
        }
        if (productDTO.getBrand() != null) {
            product.setBrand(brandService.findById(productDTO.getBrand().getId()));
        }
        List<Size> currentSizes = product.getSizes();
        List<Image> currentImages = product.getImages();

        if (productConvert.getSizes() != null && productConvert.getSizes().size() > 0) {
            for (Size s : productConvert.getSizes()) {
                if (s.getId() != null) {
                    currentSizes.stream().filter(size -> size.getId().equals(s.getId())).findFirst().ifPresent(existingSize -> {
                        implUtil.updateFieldIfNotNull(s.getName(), existingSize::setName);
                        implUtil.updateFieldIfNotNull(s.getQuantity(), existingSize::setQuantity);
                        implUtil.updateFieldIfNotNull(s.getPrice(), existingSize::setPrice);
                        implUtil.updateFieldIfNotNull(s.getSalePercent(), existingSize::setSalePercent);
                        implUtil.updateFieldIfNotNull(s.getDescription(), existingSize::setDescription);
                    });
                } else {
                    s.setProduct(product);
                    sizeRepo.save(s);
                }
            }
        }
        if (productConvert.getImages() != null && productConvert.getImages().size() > 0) {
            for (Image i : productConvert.getImages()) {
                if (i.getId() != null) {
                    currentImages.stream().filter(image -> image.getId().equals(i.getId())).findFirst().ifPresent(existingSize -> {
                        implUtil.updateFieldIfNotNull(i.getPath(), existingSize::setPath);
                        implUtil.updateFieldIfNotNull(i.isThumbnail(), existingSize::setThumbnail);
                    });
                } else {
                    i.setProduct(product);
                    imageRepo.save(i);
                }
            }
        }
        Product productSave = productRepo.save(product);
        return convertBase.convert(productSave, ProductResponse.class);
    }



    @Override
    public void delete(Long id) {

    }
}

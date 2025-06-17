## API Contract Summary (initial spec)

| API                                  | Method | Description                            |
| ------------------------------------ | ------ | -------------------------------------- |
| `/hwshop/setup`                      | `POST` | Create Laptop or Server build          |
| `/hwshop/setup/{id}/add`             | `POST` | Add Disk or StorageController to build |
| `/hwshop/storagecontroller/{id}/add` | `POST` | Add Disk to StorageController          |